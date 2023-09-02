#include <iostream>
#include <chrono>
#include "AverageTimeMeasure.hpp"


void AverageTimeMeasure::benchmarkStart()
{
    start = std::chrono::high_resolution_clock::now();
}

void AverageTimeMeasure::benchmarkStop()
{
    end = std::chrono::high_resolution_clock::now();
    elapsed += end - start;
    times++;
}

size_t AverageTimeMeasure::getAvgElapsedNsec()
{

    return std::chrono::duration_cast<std::chrono::nanoseconds>(elapsed).count() / times;
}

