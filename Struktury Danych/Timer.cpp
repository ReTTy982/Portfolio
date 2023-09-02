#include "Timer.hpp"
#include <iostream>
#include <vector>
using namespace std;
const vector<size_t> graphSize = {50, 100, 150, 200, 250};
const vector<size_t> fillFactor = {25, 50, 75, 99};

const size_t datasetGenerationloops = 20;

template <typename Algorithm>
const size_t Timer::generateMSTMatrix(const size_t &size, const size_t &fill)
{
    AverageTimeMeasure algorithmTimeAverage;
    for (size_t i = 0; i < datasetGenerationloops; i++)
    {
        MatrixGraph graph = MatrixGraph(false);
        ListGraph dummy_graph = ListGraph(false);
        RandomGraphGenerator::random(graph, dummy_graph, size, fill, false);
        algorithmTimeAverage.benchmarkStart();
        auto answer = Algorithm::generateMST(graph);
        algorithmTimeAverage.benchmarkStop();
    }
    return algorithmTimeAverage.getAvgElapsedNsec();
};
template <typename Algorithm>
const size_t Timer::generateMSTList(const size_t &size, const size_t &fill)
{
    AverageTimeMeasure algorithmTimeAverage;
    for (size_t i = 0; i < datasetGenerationloops; i++)
    {
        ListGraph graph = ListGraph(false);
        MatrixGraph dummy_graph = MatrixGraph(false);
        RandomGraphGenerator::random(dummy_graph, graph, size, fill, false);
        algorithmTimeAverage.benchmarkStart();
        auto answer = Algorithm::generateMST(graph);
        algorithmTimeAverage.benchmarkStop();
    }
    return algorithmTimeAverage.getAvgElapsedNsec();
};
template <typename Algorithm>
const size_t Timer::generatePathMatrix(const size_t &size, const size_t &fill)
{
    AverageTimeMeasure algorithmTimeAverage;
    for (size_t i = 0; i < datasetGenerationloops; i++)
    {
        MatrixGraph graph = MatrixGraph(true);
        ListGraph dummy_graph = ListGraph(true);
        RandomGraphGenerator::random(graph, dummy_graph, size, fill, true);
        algorithmTimeAverage.benchmarkStart();
        auto answer = Algorithm::getShortestPath(graph, 0);
        algorithmTimeAverage.benchmarkStop();
    }
    return algorithmTimeAverage.getAvgElapsedNsec();
}

template <typename Algorithm>
const size_t Timer::generatePathList(const size_t &size, const size_t &fill)
{
    AverageTimeMeasure algorithmTimeAverage;
    for (size_t i = 0; i < datasetGenerationloops; i++)
    {
        MatrixGraph dummy_graph = MatrixGraph(true);
        ListGraph graph = ListGraph(true);
        RandomGraphGenerator::random(dummy_graph, graph, size, fill, true);
        algorithmTimeAverage.benchmarkStart();
        auto answer = Algorithm::getShortestPath(graph, 0);
        algorithmTimeAverage.benchmarkStop();
    }
    return algorithmTimeAverage.getAvgElapsedNsec();
}

void Timer::run()
{
    
    cout << "MATRIX KRUSKAL\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generateMSTMatrix<Kruskal>(size, fill) << "\n";
        }
        cout << endl;
    }
    cout << "MATRIX PRIMA\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generateMSTMatrix<Prim>(size, fill) << "\n";
        }
        cout << endl;
    }
    cout << "LIST KRUSKAL\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generateMSTList<Kruskal>(size, fill) << "\n";
        }
        cout << endl;
    }
    cout << "LIST PRIM\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generateMSTList<Prim>(size, fill) << "\n";
        }
        cout << endl;
    }
    
    cout << "MATRIX DJIKSTRA\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generatePathMatrix<Djikstra>(size, fill) << "\n";
        }
        cout << endl;
    }
    cout << "MATRIX BELLMNA-FORD\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generatePathMatrix<BellmanFord>(size, fill) << "\n";
        }
        cout << endl;
    }
    cout << "LIST DJIKSTRA\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generatePathList<Djikstra>(size, fill) << "\n";
        }
        cout << endl;
    }
    cout << "LIST BELLMAN-FORD\n";
    for (const auto &fill : fillFactor)
    {
        for (const auto &size : graphSize)
        {
            cout << size << " " << generatePathList<BellmanFord>(size, fill) << "\n";
        }
        cout << endl;
    }
}