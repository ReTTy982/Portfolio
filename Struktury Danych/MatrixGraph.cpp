#include "MatrixGraph.hpp"
#include "Array.hpp"
#define BRESET "\x1B[0m"
#define BGREEN "\x1B[42m"
#define BMAGNETA "\x1B[45m"

using namespace std;

void MatrixGraph::addEdge(const size_t &from, const size_t &to, long long weight)
{
    size_t old_size;
    if (matrix.get_array_size() == 0)
    {
        old_size = 0;
    }
    else
    {
        old_size = matrix[0].get_array_size();
    }

    size_t new_size = std::max(from, to) + 1;

    size_t vert_size = matrix.get_array_size();
    if (new_size > old_size)
    {
        matrix.resize(new_size);
        for (size_t i = old_size; i < new_size; i++)
        {
            matrix[i].fill(new_size);
        }
    }

    for (size_t i = 0; i < matrix.get_array_size(); i++)
    {

        if (i == from && from == to)
        {
            if (new_size > old_size)
            {
                matrix[i].resize(new_size);
                matrix[i].fill(new_size);
            }

            matrix[from][from] = weight;

        }
        else if (i == from)
        {

            if (new_size > old_size)
            {
                matrix[i].resize(new_size);
                matrix[i].fill(new_size);
            }
            matrix[from][to] = weight;
            if(!isDirected){
                matrix[to][from]= weight;
            }

        }
        else
        {

            if (matrix[i].get_array_size() < new_size)
            {
                matrix[i].fill(new_size);
            }

        }

    }
}

const size_t MatrixGraph::getColumns() const
{
    if (matrix.get_array_size() != 0)
    {
        size_t max_size = 0;
        for (size_t i = 0; i < matrix.get_array_size(); i++)
        {
            size_t temp = matrix[i].get_array_size();
            max_size = std::max(max_size, temp);
        }

        return max_size;
    }
    else
        return 0;
}

const size_t MatrixGraph::getVerSize() const
{
    const size_t size = matrix.get_array_size();
    return size;
}

void MatrixGraph::print()
{
    const auto count = getColumns();
    const auto vertices = getVerSize();

    if (vertices == 0)
    {
        std::cout << BGREEN << "Graf jest pusty!" << BRESET << std::endl;

        return;
    }
    std::cout << "   " << BGREEN;
    for (size_t j = 0; j < count; j++)
    {
        std::cout << j << "  ";

        if (j < 10)
            std::cout << " ";
    }
    std::cout << BRESET << std::endl;

    for (size_t i = 0; i < vertices; i++)
    {
        std::cout << BMAGNETA << i << BRESET << " ";
        if (i < 10)
            std::cout << " ";

        for (size_t j = 0; j < count; j++)
        {
            std::cout << matrix[i][j] << " ";

            if (matrix[i][j] < 10 && matrix[i][j] > -1)
                std::cout << "  ";
            else if (matrix[i][j] > -10)
                std::cout << " ";
        }
        std::cout << std::endl;
    }

    std::cout << std::endl;
}

const IndicenceMatrix &MatrixGraph::getMatrix() const
{
    return matrix;
}

void MatrixGraph::clear(){
    matrix = IndicenceMatrix();
}