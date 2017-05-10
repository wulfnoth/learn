package org.wulfnoth.learning.storm.helloworld;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class  WordCountTopology{

    public static void main(String[] args) throws Exception
    {
        TopologyBuilder builder = new TopologyBuilder();
        //构建拓扑结构
        builder.setSpout("SentenceGenerator", new SentenceGenerator(), 5);
        builder.setBolt("SentenceSplitter", new SentenceSplitter(), 8)
                .shuffleGrouping("SentenceGenerator");
        builder.setBolt("WordCount", new WordCount(), 12)
                .fieldsGrouping("SentenceSplitter", new Fields("word"));

        Config conf = new Config();
        conf.setDebug(false);
        //创建一个本地集群并将topology提交至该集群
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("word_count", conf, builder.createTopology() );

        Thread.sleep(10000);
        cluster.shutdown();
    }
}
