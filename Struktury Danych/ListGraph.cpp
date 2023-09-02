#include "ListGraph.hpp"
#define BRESET "\x1B[0m"
#define BGREEN "\x1B[42m"
#define BMAGNETA "\x1B[45m"

void ListGraph::addEdge(const size_t &from, const size_t &to, long long weight)
{
    size_t old_size = edges.get_array_size();
    size_t new_size = std::max(from, to) + 1;

    if (new_size > old_size)
    {
        edges.resize(new_size);
    }

    edges[from].push_back(AdjacentEdge(to, weight));

    if (!isDirected)
    {
        edges[to].push_back(AdjacentEdge(from, weight));
    }
    edges_count++;
}

void ListGraph::print() const
{

    if (edges.get_array_size() == 0)
    {
        std::cout << BMAGNETA << "Graf jest pusty!" << BRESET << std::endl;
        return;
    }

    for (size_t i = 0; i < edges.get_array_size(); i++)
    {
        std::cout << BGREEN << i << BRESET << ": ";

        for (size_t j = 0; j < edges[i].get_array_size(); j++)
        {
            std::cout << "[" << edges[i][j].edge_to << ", " << edges[i][j].weight << "] ";
        }

        std::cout << std::endl;
    }

    std::cout << std::endl;
}

const size_t ListGraph::getVerSize() const
{
    return edges.get_array_size();
}

auto ListGraph::getArrayOfEdges(const size_t &vertex) const ->const Array<AdjacentEdge> &{
    return edges[vertex];

}

void ListGraph::clear(){
    edges_count = 0;
    edges = Array<Array<AdjacentEdge>>();
}