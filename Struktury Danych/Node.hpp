#pragma once

#include "MatrixGraph.hpp"
#include "ListGraph.hpp"

// struktura pomocnicza na bazie ktorej buduje tablice poprzednikow i wagi w algorytmach szukania sciezek

struct Node
{
    size_t weight = SIZE_MAX;
    size_t prev = SIZE_MAX;
    size_t current;
    Node(const size_t weight, const size_t prev, const size_t current)
    {
        this->weight = weight;
        this->prev = prev;
        this->current = current;
    };
    Node(size_t current)
    {
        this->current = current;
    };
    Node() {}

public:
    bool operator>(const Node &other) const
    {
        return weight > other.weight;
    }
    bool operator<(const Node &other) const
    {
        return weight < other.weight;
    }


};