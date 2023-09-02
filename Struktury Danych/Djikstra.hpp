#pragma once
#include "Array.hpp"
#include "BinHeap.hpp"
#include "MatrixGraph.hpp"
#include "ListGraph.hpp"
#include <iostream>
#include "Node.hpp"

class Djikstra
{
public:
    static Array<Node> getShortestPath(const MatrixGraph &graph, const size_t &from);
    static Array<Node> getShortestPath(const ListGraph &graph, const size_t &from);
    static void getShortestPathTo(const MatrixGraph &graph, const size_t &from, const size_t &to);
    static void getShortestPathTo(const ListGraph &graph, const size_t &from, const size_t &to);
};