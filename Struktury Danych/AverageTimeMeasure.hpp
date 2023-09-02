#include <iostream>
#include <chrono>
#pragma once


class AverageTimeMeasure
{
    std::chrono::high_resolution_clock::time_point start, end;
    std::chrono::duration<double> elapsed = std::chrono::duration<double>::zero();
    int times = 0;

public:
    void benchmarkStart();
    void benchmarkStop();
    size_t getAvgElapsedNsec();
};