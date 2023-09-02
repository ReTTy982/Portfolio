#pragma once
#include "MatrixGraph.hpp"
#include "ListGraph.hpp"
#include "Array.hpp"
#include "Node.hpp"
#include <iostream>

class BellmanFord{
public:
static Array<Node> getShortestPath(const MatrixGraph &graph,const size_t &from);
static Array<Node> getShortestPath(const ListGraph &graph,const size_t &from);
static void getShortestPathTo(const MatrixGraph &graph, const size_t &from, const size_t &to);
static void getShortestPathTo(const ListGraph &graph, const size_t &from, const size_t &to);

};