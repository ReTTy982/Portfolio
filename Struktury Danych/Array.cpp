#pragma once
#include <iostream>
#include "Array.hpp"
typedef int datatype;

template <typename T>
Array<T>::Array(const size_t &size)
{
    if (size == 0){
        return;
    }
    array = new T[size]();
}

template <typename T>
Array<T>::~Array()
{
    delete[] array;
}

template <typename T>
Array<T>::Array(const Array<T> &other)
{
    array_size = other.array_size;
    array = new T[array_size];
    std::copy(other.array, other.array + array_size, array);
}

template <typename T>
void Array<T>::resize(const size_t &size)
{

    T *new_array = new T[size]();

    std::copy(array, array + std::min(array_size, size), new_array);

    delete[] array;
    array = new_array;
    array_size = size;
}

template <typename T>
void Array<T>::print() const
{
    for (int i = 0; i < array_size; i++)
    {
        std::cout << array[i] << " ";
    }
    std::cout << std::endl;
}

template <typename T>
void Array<T>::push_back(const T &value)
{
    resize(array_size + 1);
    array[array_size - 1] = value;
}
template <typename T>
void Array<T>::push_front(const T &value)
{
    int new_size = array_size + 1;
    T *new_array = new T[new_size];

    std::copy(array, array + array_size, new_array + 1);
    new_array[0] = value;

    delete[] array;
    array = new_array;
    array_size = new_size;
}

template <typename T>
void Array<T>::index_check(const size_t &index)
{
    if (index >= array_size)
    {
        throw std::out_of_range("Index is out of array's range\n");
    }
}

template <typename T>
void Array<T>::push(const T &value, const size_t &index)
{
    index_check(index);

    size_t new_size = array_size + 1;
    T *new_array = new T[new_size];

    std::copy(array, array + index, new_array);
    std::copy(array + index, array + array_size, new_array + index + 1);

    new_array[index] = value;

    delete[] array;
    array = new_array;
    array_size = new_size;
}

template <typename T>
void Array<T>::pop_index(const T &index)
{
    index_check(index);

    size_t new_size = array_size - 1;
    T *new_array = new T[new_size];

    std::copy(array, array + index, new_array);
    std::copy(array + index + 1, array + array_size, new_array + index);

    delete[] array;
    array = new_array;
    array_size = new_size;
}

template <typename T>
void Array<T>::pop_back()
{
    resize(array_size - 1);
}

template <typename T>
void Array<T>::pop_front()
{

    size_t new_size = array_size - 1;
    T *new_array = new T[new_size];

    std::copy(array + 1, array + array_size, new_array);

    delete[] array;

    array = new_array;
    array_size = new_size;
}

template <typename T>
int Array<T>::get_index(const T &value){

    for (int i = 0; i < array_size; i++)
    {
        if (array[i] == value)
            return i;
    }
    return false;

}

template <typename T>
void Array<T>::pop(const T &value){
    int index = get_index(value);
    pop_index(index);
}

template <typename T>
bool Array<T>::search(const T &value)
{
    for (int i = 0; i < array_size; i++)
    {
        if (array[i] == value)
            return true;
    }
    return false;
}
template <typename T>
const size_t Array<T>::get_array_size()  const
{
    return array_size;
}

template <typename T>
T &Array<T>::get_value(const int &at)
{

    if (at >= array_size)
    {

        throw std::out_of_range("Array index out of range?");
    }

    return array[at];
}

template <typename T>
T &Array<T>::operator[](const size_t &at)
{
    if (at >= array_size){
        std::cout <<std::endl<< at << " " << array_size;
        throw std::out_of_range("Array index out of range!");
    }
    return array[at];
}
template <typename T>
auto Array<T>::operator=(const Array<T> &other) -> Array<T> &
{
    array_size = other.array_size;
    array = new T[array_size];
    std::copy(other.array, other.array + array_size, array);
    return *this;
}


template <typename T>
const T &Array<T>::operator[](const size_t &at) const
{
    if (at >= array_size)
        throw std::out_of_range("Array index out of range!!");
    return array[at];
}

template<typename T>
void Array<T>::fill(size_t index){
    size_t size = get_array_size();
    for(size;size<index;){
        push_back(0);
        size = get_array_size();
    }
}

