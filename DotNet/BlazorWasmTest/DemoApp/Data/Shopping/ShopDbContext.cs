using DemoApp.Client.Shared;
using Microsoft.EntityFrameworkCore;

namespace DemoApp.Data.Shopping;

public class ShopDbContext(DbContextOptions options) : DbContext(options)
{
    public DbSet<CustomerLogin> Customers { get; set; } = default!;

    public DbSet<Order> Orders { get; set; } = default!;

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<CustomerLogin>()
            .ToTable("CustomerInfo")
            .HasKey(p => p.UserName);
    }
}
