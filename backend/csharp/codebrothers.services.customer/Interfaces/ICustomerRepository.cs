using System.Collections.Generic;
using System.Threading.Tasks;
using codebrothers.services.customer.Entities;

namespace codebrothers.services.customer.Interfaces
{
    public interface ICustomerRepository
    {
        Task<List<Entities.Customer>> FindAll();
        Task<Customer> Find(long id);
        Task Update(Customer c);
        Task Delete(Customer c);
        Task<Customer> Insert(Customer customer);
    }
}