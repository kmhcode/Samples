namespace DemoApp.Models;

public class Inventory
{
    public Dictionary<string, ItemInfo> Store { get; } = [];

    public Inventory()
    {   
        Store = File.ReadAllLines("medstore.csv")
            .Skip(1)
            .Where(line => line.Length > 0)
            .Select(line => line.Split(','))
            .ToDictionary(segs => segs[0].ToLower(), segs => new ItemInfo(
                UnitPrice: double.Parse(segs[1]),
                StockLevel: int.Parse(segs[2])
            ));
    }
}