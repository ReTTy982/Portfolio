#pragma once

#include <iostream>
#include "Array.hpp"
#include "Edge.hpp"

class ListGraph
{
public:
    struct AdjacentEdge
    {

        size_t edge_to;
        long long weight;

        AdjacentEdge(size_t edge_to, long long weight){
            this->edge_to = edge_to;
            this-> weight = weight;
        };
        AdjacentEdge(){};
    };
    ListGraph(bool isDirected){
        this->isDirected = isDirected;
    };
    void addEdge(const size_t &from, const size_t &to, long long weight);
    void print() const;
    const size_t getVerSize() const;
    const Array<AdjacentEdge> &getArrayOfEdges(const size_t &vertex) const;
    void clear();

private:
    Array<Array<AdjacentEdge>> edges;
    bool isDirected;
    size_t edges_count;
};