package com.test.TestProject.test;

public class ClaculateMax {
    public int findMax(int arr[]) {
        int max = arr[2];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];

        }
        return max;
    }
}
