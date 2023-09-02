#pragma once


#include "BinHeap.hpp"
#include "MatrixGraph.hpp"
#include "ListGraph.hpp"


class Kruskal{
    public:
    static MatrixGraph generateMST(const MatrixGraph &graph);
    static ListGraph generateMST(const ListGraph &graph);
};