#include "BellmanFord.hpp"

Array<Node> BellmanFord::getShortestPath(const MatrixGraph &graph, const size_t &from)
{
    const size_t &vert = graph.getVerSize();
    const IndicenceMatrix &matrix = graph.getMatrix();

    Array<Node> pathWeights;

    for (size_t i = 0; i < vert; i++)
    {
        pathWeights.push_back(Node(i));
    }

    if (vert == 0)
    {
        return pathWeights;
    }

    pathWeights[from] = Node(0, from, from);

    for (size_t loop_count = 0; loop_count < vert - 1; loop_count++)
    {

        for (size_t start_vert = 0; start_vert < vert; start_vert++)
        {
            for (size_t end_vert = 0; end_vert < vert; end_vert++)
            {
                if (matrix[start_vert][end_vert] > 0)
                {
                    if (matrix[start_vert][end_vert] + pathWeights[start_vert].weight < pathWeights[end_vert].weight)
                    {
                        pathWeights[end_vert].weight = matrix[start_vert][end_vert] + pathWeights[start_vert].weight;
                        pathWeights[end_vert].prev = start_vert;
                    }
                }
            }
        }
    }
    return pathWeights;
}

Array<Node> BellmanFord::getShortestPath(const ListGraph &graph, const size_t &from)
{

    const size_t &vert = graph.getVerSize();
    Array<Node> pathWeights;

    for (size_t i = 0; i < vert; i++)
    {
        pathWeights.push_back(Node(i));
    }

    if (vert == 0)
    {
        return pathWeights;
    }

    pathWeights[from] = Node(0, from, from);

    for (size_t loop_count = 0; loop_count < vert - 1; loop_count++)
    {
        for (size_t start_vert = 0; start_vert < vert; start_vert++)
        {
            const auto &edges_array = graph.getArrayOfEdges(start_vert);
            for (size_t i = 0; i < edges_array.get_array_size(); i++)
            {
                if (edges_array[i].weight + pathWeights[start_vert].weight < pathWeights[edges_array[i].edge_to].weight)
                {
                    pathWeights[edges_array[i].edge_to].weight = edges_array[i].weight + pathWeights[start_vert].weight;
                    pathWeights[edges_array[i].edge_to].prev = start_vert;
                }
            }
        }
    }
    return pathWeights;
}

void BellmanFord::getShortestPathTo(const MatrixGraph &graph, const size_t &from, const size_t &to)
{
    Array<Node> temp = getShortestPath(graph, from);
    if (from >= graph.getVerSize() || to >= graph.getVerSize())
    {
        std::cout << "Niepoprawne dane wejściowe!\n";
        return;
    }
    Array<size_t> array;
    if (temp[to].weight == SIZE_MAX)
    {
        std::cout << "Sciezka nieosiagalna\n";
        return;
    }
    array.push_front(to);
    size_t prevVertex = temp[to].prev;

    for (size_t i = 0; i < temp.get_array_size(); i++)
    {

        if (prevVertex == from)
        {
            array.push_front(prevVertex);
            break;
        }

        size_t vertex = prevVertex;

        array.push_front(vertex);
        prevVertex = temp[vertex].prev;
    }

    for (size_t i = 0; i < array.get_array_size() - 1; i++)
    {
        std::cout << array[i] << "-> ";
    }
    std::cout << array[array.get_array_size() - 1] << std::endl;
    std::cout << "Koszt: " << temp[to].weight << std::endl;
}

void BellmanFord::getShortestPathTo(const ListGraph &graph, const size_t &from, const size_t &to)
{
    Array<Node> temp = getShortestPath(graph, from);
    if (from >= graph.getVerSize() || to >= graph.getVerSize())
    {
        std::cout << "Niepoprawne dane wejściowe!\n";
        return;
    }
    Array<size_t> array;
    if (temp[to].weight == SIZE_MAX)
    {
        std::cout << "Sciezka nieosiagalna\n";
        return;
    }
    array.push_front(to);
    size_t prevVertex = temp[to].prev;

    for (size_t i = 0; i < temp.get_array_size(); i++)
    {

        if (prevVertex == from)
        {
            array.push_front(prevVertex);
            break;
        }

        size_t vertex = prevVertex;

        array.push_front(vertex);
        prevVertex = temp[vertex].prev;
    }

    for (size_t i = 0; i < array.get_array_size() - 1; i++)
    {
        std::cout << array[i] << "-> ";
    }
    std::cout << array[array.get_array_size() - 1] << std::endl;
    std::cout << "Koszt: " << temp[to].weight << std::endl;
}