package ru.job4j.algo.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        int currStartTime = 0;
        int currCount = 0;
        int maxCount = 0;
        List<Event> events = new ArrayList<>();
        visitTimes.forEach(arr -> {
            events.add(new Event(arr[0], EventType.ARRIVAL));
            events.add(new Event(arr[1], EventType.DEPARTURE));
        });
        Collections.sort(events);
        for (var e : events) {
            if (e.type == EventType.ARRIVAL) {
                currCount++;
                currStartTime = e.time;
            } else {
                if (maxCount < currCount) {
                    maxCount = currCount;
                    currCount--;
                    maxLoadStartTime = currStartTime;
                    maxLoadEndTime = e.time;
                }
                currCount--;
            }
        }
        System.out.println("Test1");
        System.out.println("Test2");
        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}

