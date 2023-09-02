#pragma once
#include <iostream>
#include "BinHeap.hpp"

template <typename T>
int BinHeap<T>::parent(int index)
{
    return (index - 1) / 2;
}

template <typename T>
int BinHeap<T>::left(int index)
{
    return (index * 2) + 1;
}

template <typename T>
int BinHeap<T>::right(int index)
{
    return (index * 2) + 2;
}

template <typename T>
int BinHeap<T>::look_for_index(const T &value)
{
    int index = 0;
    while (index != array.get_array_size())
    {
        if (array.get_value(index) == value)
        {
            return index;
        }
        index++;
    }
    return -1;
}

template <typename T>
bool BinHeap<T>::search(const T &value)
{
    return array.search(value);
}

template <typename T>
void BinHeap<T>::push(const T &value)
{

    array.push_back(value);

    int i = array.get_array_size() - 1;

    // Naprawa wlasnosci kopca, dopoki wstawiony element jest wiekszy od rodzica, zamieniaj
    // rodzica i dziecko

    while (i != 0 && array[parent(i)] > array[(i)])
    {
        std::swap(array[(i)], array[parent(i)]);
        i = parent(i);
    }
}

template <typename T>
bool BinHeap<T>::pop(int index)
{
    int max_index = array.get_array_size() - 1;

    // std::cout << "JAKA WARTOSC: " <<array[index] << std::endl;

    if (index < -1) // wartosci nie ma w kopcu
    {
        return false;
    }
    // zamieniamy wartosc usuwanego wezla na wartosc ostatniego liscia kopca
    //  i zmniejszamy dlugosc kopca, usuwajac przy tym ostatni lisc
    array[index] = array[max_index];
    array.resize(max_index);

    if (index == max_index) // usuwany wezel byl ostatnim lisciem kopca
    {
        return true;
    }

    // naprawa kopca

    while (1)
    {
        // w tej petli
        int temp = index; // kandydat na zmiane, bedzie to wieksze dziecko naszego wezla

        if (left(index) < max_index && array[temp] > array[left(index)])
        {
            temp = left(index);
        }

        if (right(index) < max_index && array[temp] > array[right(index)])
        {
            temp = right(index);
        }

        if (temp != index)
        {
            std::swap(array[index], array[temp]);
            index = temp;
        }
        else
        {

            break;
        }
    }

    return true;
}
template <typename T>
const Array<T> &BinHeap<T>::get_array() const
{
    return array;
}

template <typename T>
const T &BinHeap<T>::top() const
{
    return array[0];
}
/*
template <typename T>
void BinHeap<T>::print()
{
    int row = 0, pow = 1;

    for (int i = 0; i < array.get_array_size(); i++)
    {

        std::cout << array.get_value(i) << " ";
        if (i == row)
        {

            pow *= 2;
            row += pow;
            std::cout << std::endl;
        }
    }

    std::cout << std::endl;
}
*/
