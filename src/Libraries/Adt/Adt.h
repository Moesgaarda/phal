template <typename Type>
class Adt
{
  protected:
    Type var;
    bool status;
  public:
    Adt() {}
    Adt(const Type &initValue) : var(initValue) {}
    virtual void On();
    virtual void Off();
    void ToggleOnOff(){ status ? Off() : On(); }
};