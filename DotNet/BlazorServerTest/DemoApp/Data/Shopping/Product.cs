using System.ComponentModel.DataAnnotations.Schema;

namespace Data.Shopping;

[Table("ProductInfo")]
public class Product
{
    [Column("ProductNo")]
    public int Id { get; set; }

    public int Stock { get; set; }

    public decimal Price { get; set; }
}
