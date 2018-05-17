#ifndef PhalGroup_h
#define PhalGroup_h

#include "PhalList.h"
#include "Adt.h"


class PhalGroup 
{
  private:
    PhalList<Adt*> list;
  public:
    PhalGroup();
    void add(Adt *item);
    void on();
    Adt* get(int index);

    int size;
};

PhalGroup::PhalGroup()
{
}

void PhalGroup::add(Adt *item){
  list.add(item);
}

void PhalGroup::on(){
  int lstSize = list.size();
  for(int i = 0; i < lstSize; i++)
  {
    list.get(i)->on();
  }
}
Adt* PhalGroup::get(int index){
  return list.get(index);
}


#endif
