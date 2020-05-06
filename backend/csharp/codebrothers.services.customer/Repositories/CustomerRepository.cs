using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.Extensions.Logging;
using codebrothers.services.customer.Entities;
using codebrothers.services.customer.Interfaces;

namespace codebrothers.services.customer.Repositories
{
    public class CustomerRepository : ICustomerRepository
    {
        private readonly PostgreContext context;
        private readonly ILogger<CustomerRepository> logger;
        public CustomerRepository(ILogger<CustomerRepository> logger, PostgreContext context)
        {
            this.logger = logger;
            this.context = context;
        }
        public async Task<Customer> Find(long id)
        {
            return await context.FindAsync<Customer>(id);
        }

        public async Task<List<Entities.Customer>> FindAll()
        {
            return await Task.Run(() => { return context.Customers.ToList(); });
        }

        public async Task Update(Customer c)
        {
            await Task.Run(() =>
            {
                context.Customers.Update(c);
                context.SaveChanges();
            });
        }

        public async Task Delete(Customer c)
        {
            await Task.Run(() =>
            {
                context.Customers.Remove(c);
                context.SaveChanges();
            });
        }

        public async Task<Customer> Insert(Customer customer)
        {
            await context.Customers.AddAsync(customer);
            await Task.Run(() =>
            {
                context.SaveChanges();
            });

            return customer;
        }
    }
}