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
#endif