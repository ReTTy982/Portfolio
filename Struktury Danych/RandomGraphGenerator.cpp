#include "RandomGraphGenerator.hpp"

const static size_t MAX_COST = 100;

size_t RandomGraphGenerator::randomNumberWithinRange(const size_t &first, const size_t &second)
// generator losowych liczb
{
    std::uniform_int_distribution<size_t> distribution(first, second);
    return distribution(generator);
}

void RandomGraphGenerator::random(MatrixGraph &mgraph, ListGraph &lgraph, size_t vertexCount, size_t fillFactor, const bool &isDirected)
{
    if(fillFactor>99){
        std::cout << "Gestosc nie moze byc wieksza niz 99\n";
        return;
    }
    if(vertexCount<=1){
        std::cout << "Niepoprawne dane wejsciowe\n";
        return;
    }

    size_t edges;
    edges = vertexCount * (vertexCount - 1); // obliczanie ilosci potrzebnych krawedzi

    if (!isDirected) // dwa razy mniej dla grafu nieskierowanego
    {
        edges /= 2;
    }
    edges *= 0.01 * fillFactor;
    std::vector<size_t> neededVert, seenFrom, seenTo, aviableVert;

    for (size_t i = 0; i < vertexCount; i++)
    {

        neededVert.push_back(i);
        aviableVert.push_back(i);
    }
    // mieszanie wartosci w tablicach potrzebnych i dostepnych wierzcholkow
    std::shuffle(neededVert.begin(), neededVert.end(), generator);
    std::shuffle(aviableVert.begin(), aviableVert.end(), generator);

    while (neededVert.size() != 1) // konstrukcja grafu spojnego
    {
        size_t from = neededVert.back();
        neededVert.pop_back();
        size_t to = neededVert.back();
        seenFrom.push_back(from);
        seenTo.push_back(to);
        size_t weight = randomNumberWithinRange(1, MAX_COST);
        mgraph.addEdge(from, to, weight);
        lgraph.addEdge(from, to, weight);

        if (!isDirected)
        {
            seenFrom.push_back(to);
            seenTo.push_back(from);
        }
        edges--;
    }
    const IndicenceMatrix &matrix = mgraph.getMatrix();

    // petla znajdujaca dostepne jeszcze krawedzie i losowo dodajaca je do grafu
    while (edges != 0)
    {
        size_t from = aviableVert.back();

        std::vector<size_t> aviableTo;

        for (size_t i = 0; i < matrix.get_array_size(); i++)
        {

            if (matrix[from][i] == 0 && from != i)
            {

                aviableTo.push_back(i);
            }
        }

        if (aviableTo.size() != 0)
        {
            std::shuffle(aviableVert.begin(), aviableVert.end(), generator);
            size_t weight = randomNumberWithinRange(1, MAX_COST);
            mgraph.addEdge(from, aviableTo.back(), weight);
            lgraph.addEdge(from, aviableTo.back(), weight);

            edges--;
        }
        else
        {

            aviableVert.pop_back();
        }
    }
}
