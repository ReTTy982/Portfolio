#pragma once
#include <iostream>
#include <chrono>
#include "Kruskal.hpp"
#include "RandomGraphGenerator.hpp"
#include "AverageTimeMeasure.hpp"
#include <vector>
#include "Prim.hpp"
#include "Djikstra.hpp"
#include "BellmanFord.hpp"

using namespace std;

typedef unsigned long long timedata;



class Timer{
template <typename Algorithm>    
const size_t generateMSTMatrix(const size_t &size, const size_t &fill);
template <typename Algorithm>   
const size_t generateMSTList(const size_t &size, const size_t &fill);
template <typename Algorithm>
const size_t generatePathMatrix(const size_t &size, const size_t &fill);
template <typename Algorithm>
const size_t generatePathList(const size_t &size, const size_t &fill);



public:
void run();
};