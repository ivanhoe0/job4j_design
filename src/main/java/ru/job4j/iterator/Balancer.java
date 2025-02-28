package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        CyclicIterator<Integer> cycle = new CyclicIterator<>(
                IntStream.range(0, nodes.size())
                        .boxed()
                        .collect(Collectors.toList())
        );
        while (source.hasNext()) {
            nodes.get(cycle.next()).add(source.next());
        }
    }
}
