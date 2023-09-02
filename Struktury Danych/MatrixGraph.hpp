#pragma once

#include <iostream>

#include "Array.hpp"
#include "Edge.hpp"

typedef Array<long long> NodeMatrix;
typedef Array<NodeMatrix> IndicenceMatrix;

class MatrixGraph
{

public:
    IndicenceMatrix matrix;
    bool isDirected;
    MatrixGraph(bool isDirected){
        this->isDirected = isDirected;
    };

    void addEdge(const size_t &from, const size_t &to, long long weight);

    void print();
    void clear();
    const size_t getColumns() const;
    const size_t getVerSize() const ;
    const IndicenceMatrix &getMatrix() const;
};