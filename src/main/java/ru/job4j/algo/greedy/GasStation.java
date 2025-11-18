package ru.job4j.algo.greedy;

class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int start = 0;
        int tank = 0;
        int currStation = 0;
        for (int i = 0; i < gas.length; i++) {
            start = i;
            while (currStation < gas.length) {
                int index = (i + currStation++) % gas.length;
                tank += gas[index] - cost[index];
                if (tank < 0) {
                    tank = 0;
                    currStation = 0;
                    i += currStation;
                    totalGas = 0;
                    totalCost = 0;
                    break;
                }
                totalGas += gas[index];
                totalCost += cost[index];
            }
            if (currStation == gas.length) {
                break;
            }
        }
        return totalGas >= totalCost && totalGas != 0 ? start : -1;
    }
}