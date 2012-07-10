package june;

public abstract class Location implements Vector3
{
    public Location(){

    }

    public void adjust(Vector3 dir)
    {
      adjust(dir, 1.0);
    }
    
    public void adjust(Vector3 dir, double scale) {
      dir.times(scale);

      add(dir);
    }

    public abstract void add(Vector3 v);
    public abstract void times(double d);
}
