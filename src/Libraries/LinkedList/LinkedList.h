/*
    LinkedList for use with the PHAL language
*/

#ifndef LinkedList_h
#define LinkedList_h

#include <stddef.h>

template <class T>
struct ListNode
{
    T data;
    ListNode<T> *next;
};

template <typename T>
class LinkedList
{
  protected:
    int _size;
    ListNode<T> *root;
    ListNode<T> *last;

    ListNode<T> *getNode(int index);

  public:
    LinkedList();

    // Add new element to list, remember to increase size
    virtual bool add(T);

    // Get element at specified position of list
    virtual T get(int index);

    // Remove list at given index
    virtual T remove(int index);
};

template <typename T>
LinkedList<T>::LinkedList()
{
    root = NULL;
    last = NULL;
    _size = 0;
}

// Add a new node to the list
template <typename T>
bool LinkedList<T>::add(T _t)
{
    ListNode<T> *tmp = new ListNode<T>();
    tmp->data = _t;
    tmp->next = NULL;

    if (root)
    {
        last->next = tmp;
        last = tmp;
    }
    else
    {
        root = tmp;
        last = tmp;
    }

    _size++;

    return true;
}

template <typename T>
T LinkedList<T>::get(int index)
{
    ListNode<T> *tmp = getNode(index);
    return (tmp ? tmp->data : T());
}

// Helper method to get
template <typename T>
ListNode<T> *LinkedList<T>::getNode(int index)
{
    int _pos = 0;
    ListNode<T> *current = root;

    while (_pos < index)
    {
        current = current->next;
        _pos++;
    }

    if (_pos == index)
    {
        return current;
    }

    return current;
}

// Remove element at given index
template <typename T>
T LinkedList<T>::remove(int index)
{
    if (index < 0 || index >= _size)
        return T();

    ListNode<T> *tmp = getNode(index - 1);
    ListNode<T> *toDelete = tmp->next;
    T ret = toDelete->data;
    tmp->next = tmp->next->next;
    delete (toDelete);
    _size--;
    return ret;
}

#endif