#include "Prim.hpp"

MatrixGraph Prim::generateMST(const MatrixGraph &graph)
{

    const size_t &edges = graph.getColumns();
    const size_t &vert = graph.getVerSize();
    const IndicenceMatrix &matrix = graph.getMatrix();

    MatrixGraph mstGraph = MatrixGraph(false);
    BinHeap<Edge> queue; // kolejka priorytetowa przetrzymujaca krawedzie wierzcholkow juz prztworzonych
    Array<bool> visited; // tablica booli pokazujaca ktore wierzcholki odwiedzilismy
    for (size_t i = 0; i < vert; i++)
    {
        visited.push_back(false);
    }

    for (size_t j = 0; j < vert; j++) // petla ktora dodaje krawedzie pierwszego wierzcholka do kolejki
    {
        for (size_t i = 0; i < vert; i++)
        {
            if (matrix[j][i] != 0)
            {
                queue.push(Edge(j, i, matrix[j][i]));
            }
        }
        if (queue.get_array().get_array_size() != 0)
        {
            visited[j] = true; // j to nasz startowy wierzcholek, odwiedzamy go wiec ustawiamy jego wartosc w tablicy visited na true
            break;
        }
    }
    for (size_t addedEdges = 0; addedEdges < vert - 1;)
    {
        Edge candidate = queue.top(); // Bierzemy najmniejsza dostepna krawedz, usuwamy ja i sprawdzamy czy idzie do nieodwiedzonego wierzchlka
        queue.pop(0);

        if (!visited[candidate.to]) // jesli tak to ustawiamy wartosc w visted koncowego wierzcholka na true i dodajemy krawedz do grafu
        {
            visited[candidate.to] = true;
            mstGraph.addEdge(candidate.from, candidate.to, candidate.weight);
            for (size_t i = 0; i < vert; i++) // dodajemy wszystkie krawedzie nowo odwiedzonego wierzcholka do kolejki
            {
                if (matrix[candidate.to][i] != 0)
                {

                    queue.push(Edge(candidate.to, i, matrix[candidate.to][i]));
                }
            }
            addedEdges++;
        }
    }

    return mstGraph;
}

ListGraph Prim::generateMST(const ListGraph &graph)
{
   

    const size_t &vert = graph.getVerSize();
    ListGraph mst = ListGraph(false);
    if (vert == 0)
    {
        return mst;
    }

    Array<bool> visited;
    BinHeap<Edge> queue;

    for (size_t i = 0; i < vert; i++)
    {
       

        visited.push_back(false);
    }

    for (size_t i = 0; i < vert; i++)
    {
        

        const auto &edges_array = graph.getArrayOfEdges(i);
        for (size_t j = 0; j < edges_array.get_array_size(); j++)
        {
           

            queue.push(Edge(i, edges_array[j].edge_to, edges_array[j].weight));
        }
        if (queue.get_array().get_array_size() != 0)
        {
            

            visited[i] = true;


            break;
        }
    }

    for (size_t addedEdges = 0; addedEdges < vert - 1;)
    {
       
        Edge candidate = queue.top();
        queue.pop(0);
       
        
        if (!visited[candidate.to])
        {
            

            visited[candidate.to] = true;
            mst.addEdge(candidate.from, candidate.to, candidate.weight);
            const auto &edges_array = graph.getArrayOfEdges(candidate.to);
            

            for (size_t i = 0; i < edges_array.get_array_size(); i++)
            {
                queue.push(Edge(candidate.to, edges_array[i].edge_to, edges_array[i].weight));
            }
            addedEdges++;
        }
    }
    return mst;
}