namespace codebrothers.services.customer.Interfaces
{
    public interface IPostgreContext
    {
        public Microsoft.EntityFrameworkCore.DbSet<Entities.Customer> Customers { get; set; }
    }
}