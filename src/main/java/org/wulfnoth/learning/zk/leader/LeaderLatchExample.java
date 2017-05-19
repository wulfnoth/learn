package org.wulfnoth.learning.zk.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 */
public class LeaderLatchExample {

    private static final String PATH = "/leader/example";

    private static final int QTY = 5;

    private static final Map<String, LeaderLatch> map = new HashMap<>();

    private static String currentLeaderThread = null;

    public static void main(String[] args) throws Exception {
        TestingServer server = new TestingServer();
        server.start();

        for (int i=0; i<QTY; i++) {
            int finalI = i;
            new Thread(() -> {
                CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(),
                        new ExponentialBackoffRetry(1000, 3));
                client.start();
                LeaderLatch ll = new LeaderLatch(client, PATH);
                try {
                    ll.addListener(new LeaderLatchListenerExample());
                    ll.start();
                    map.put("LeaderLatch => " + finalI, ll);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "LeaderLatch => " + i).start();
        }
        /*for(String key : map.keySet()) {
            System.out.println(key + "\t" + map.get(key).getId());
        }
        System.out.println(currentLeaderThread);*/
        Thread.sleep(3000);
        map.get(currentLeaderThread).close(LeaderLatch.CloseMode.NOTIFY_LEADER);
        Thread.sleep(3000);
        map.get(currentLeaderThread).close(LeaderLatch.CloseMode.NOTIFY_LEADER);
        Thread.sleep(3000);
        map.get(currentLeaderThread).close(LeaderLatch.CloseMode.NOTIFY_LEADER);
        Thread.sleep(3000);
        map.get(currentLeaderThread).close(LeaderLatch.CloseMode.NOTIFY_LEADER);
        Thread.sleep(3000);
        map.get(currentLeaderThread).close(LeaderLatch.CloseMode.NOTIFY_LEADER);
    }

    private static class LeaderLatchListenerExample implements LeaderLatchListener {

        @Override
        public void isLeader() {
            System.out.println(Thread.currentThread().getName() + " is leader.");
            currentLeaderThread = Thread.currentThread().getName().substring(0, 16);
        }

        @Override
        public void notLeader() {
            System.out.println(Thread.currentThread().getName() + " not leader.");
        }
    }

}
