#pragma once

#include <iostream>

template <typename T>
class Array
{
    size_t array_size = 0;
    T *array = nullptr;

public:
    Array(const std::size_t &size = 0);
    Array(const Array<T> &other);
    ~Array();

    void resize(const size_t &size);

    void print() const;

    void push_back(const T &value);

    void push_front(const T &value);

    void pop_back();

    void pop_front();

    void push(const T &value, const std::size_t &index);

    void fill(size_t index);

    void pop_index(const T &index);

    void index_check(const std::size_t &index);

    const size_t get_array_size() const;

    bool search(const T &value);

    T &get_value(const int &at);

    int get_index(const T &value);
    void pop(const T &value);

    T &operator[](const std::size_t &at);
    const T &operator[](const std::size_t &at) const;
    Array &operator=(const Array &other);
};

#include "Array.cpp"