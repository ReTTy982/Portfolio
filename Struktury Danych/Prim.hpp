#pragma once

#include "MatrixGraph.hpp"
#include <iostream>
#include "BinHeap.hpp"
#include "ListGraph.hpp"

class Prim{
    public:
static MatrixGraph generateMST(const MatrixGraph &graph);
static ListGraph generateMST(const ListGraph &graph);
};