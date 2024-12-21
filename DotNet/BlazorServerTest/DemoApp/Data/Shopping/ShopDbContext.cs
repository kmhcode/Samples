using Microsoft.EntityFrameworkCore;

namespace Data.Shopping;

public class ShopDbContext(DbContextOptions options) : DbContext(options)
{
    public DbSet<Product> Products { get; set; } = default!;
}
