using System.Collections.Generic;
using System.Threading.Tasks;
using codebrothers.services.customer.Entities;

namespace codebrothers.services.customer.Interfaces
{
    public interface ICustomerService
    {
        Task<List<Entities.Customer>> FindAll();
        Task<Customer> Find(long id);
        Task<Customer> Insert(Customer customer);
        Task<Customer> Update(long id, Customer customer);
        Task Delete(long id);
    }
}