package org.wulfnoth.learning.storm.helloworld;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * @author young
 */
public class WordCount extends BaseBasicBolt {
    private Map<String, Integer> counts = new HashMap<>();

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector){
        String word = tuple.getString(0);
        int count = counts.getOrDefault(word, 0);
        count++;
        counts.put(word,count);
        //发送单词和计数（分别对应字段word和count）
        collector.emit(new Values(word, count));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer){
        declarer.declare(new Fields("word","count"));
    }
}
