package com.emor.dbfinal.conponent.strategy.student;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class StudentStrategyFactory {
    private static HashMap<String,StudentQueryStrategy> map = new HashMap<>();
    public static StudentQueryStrategy getStrategy(String id){
        return map.get(id);
    }
    public static void register(String id,StudentQueryStrategy strategy){
        map.put(id,strategy);
    }
    static {
    }
    public static HashMap<String,StudentQueryStrategy> getQueries(){
        return map;
    }
}

