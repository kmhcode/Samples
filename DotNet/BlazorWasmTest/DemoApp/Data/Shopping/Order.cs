using System.ComponentModel.DataAnnotations.Schema;

namespace DemoApp.Data.Shopping;

[Table("OrderDetail")]
public class Order
{
    [Column("OrderNo")]
    public int Id { get; set; }

    public DateOnly OrderDate { get; set; }

    public required string CustomerId { get; set; }

    [Column("ProductNo")]
    public int ProductId { get; set; }

    public int Quantity { get; set; }
}
