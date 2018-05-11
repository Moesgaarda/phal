template <typename Type>
class Adt
{
  protected:
    Type var;

  public:
    Adt() {}
    Adt(const Type &initValue) : var(initValue) {}
};