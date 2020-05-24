using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace codebrothers.services.customer.Repositories
{
    public class PostgreContext : DbContext

    {
        public PostgreContext(DbContextOptions<PostgreContext> options) : base(options)
        {
        }

        public DbSet<Entities.Customer> Customers { get; set; }
    }
}