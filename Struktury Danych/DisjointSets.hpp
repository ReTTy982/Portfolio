#pragma once

#include <iostream>
#include "Array.hpp"

class DisjointSets{

    struct Node{
        size_t parent = -1;
        size_t rank = 0;
    };

    Array<Node> array;

public:

    DisjointSets(const size_t &size);
    const size_t &findNode(const size_t &node);
    void nodeUnion(const size_t &nodeA, const size_t &nodeB);



};
