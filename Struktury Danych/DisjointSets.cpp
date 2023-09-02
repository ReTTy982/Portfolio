#include "DisjointSets.hpp"

DisjointSets::DisjointSets(const size_t &size)
{
    array.resize(size);
}

const size_t &DisjointSets::findNode(const size_t &node)
{

    if (array[node].parent == -1)
    {

        return node;
    }

    array[node].parent = findNode(array[node].parent);

    return array[node].parent;
}

void DisjointSets::nodeUnion(const size_t &nodeA, const size_t &nodeB)
{
    const auto &parentA = findNode(nodeA);
    const auto &parentB = findNode(nodeB);

    if (array[parentA].rank < array[parentB].rank)
        array[parentA].parent = parentB;
    else if (array[parentA].rank > array[parentB].rank)
        array[parentB].parent = parentA;
    else if (parentA != parentB)
    {
        array[parentB].parent = parentA;
        array[parentA].rank += 1;
    }
}