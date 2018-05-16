#ifndef PhalList_h
#define PhalList_h

#include <stddef.h>
template <class T>
struct Node
{
  T data;
  Node<T> *next;
};


template <typename T>
class PhalList
{
  private:
    Node<T> *root;
    Node<T> *last;
    int _size;
  public:
    PhalList();
    int size();
    void add(T _t);
    T get(int index);
    void remove(int index);
};
template <typename T>
PhalList<T>::PhalList()
{
  root = new Node<T>();
  last = root;
  _size = 0;
}
template <typename T>
int PhalList<T>::size()
{
  return _size;
}
template <typename T>
void PhalList<T>::add(T _t)
{
  Node<T> *tmp = new Node<T>();
  tmp->data = _t;
  tmp->next = NULL;

  last->next = tmp;
  last = tmp;

  _size++;
}
template <typename T>
T PhalList<T>::get(int index)
{
  if(index > _size){
    return false;
  }
  Node<T> *tmp = new Node<T>();
  tmp = root->next;
  for(int i = 0; i < index; i++)
  {
    tmp = root->next;
  }
  return tmp->data;
}
template <typename T>
void PhalList<T>::remove(int index)
{
    if(index > _size){
    return false;
  }
  Node<T> *removedNode = new Node<T>();
  Node<T> *tmp = new Node<T>();
  removedNode = root;
  int i;
  for(i = 0; i <= index; i++)
  {
    tmp = removedNode;
    removedNode = removedNode->next;
    if(i==index){
      tmp->next = removedNode->next;
    }
  }
  delete removedNode;
  _size--;
}




#endif
