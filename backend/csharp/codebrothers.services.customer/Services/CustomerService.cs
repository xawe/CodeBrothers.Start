using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.Extensions.Logging;
using codebrothers.services.customer.Entities;
using codebrothers.services.customer.Exceptions;
using codebrothers.services.customer.Interfaces;
using codebrothers.services.customer.Repositories;

namespace codebrothers.services.customer.Services
{
    public class CustomerService : ICustomerService
    {
        ICustomerRepository repository;
        private readonly ILogger<CustomerRepository> logger;
        public CustomerService(ILogger<CustomerRepository> logger, ICustomerRepository repository)
        {
            this.logger = logger;
            this.repository = repository;
        }
        public async Task<List<Customer>> FindAll()
        {
            return await repository.FindAll();
        }

        public async Task<Customer> Find(long id)
        {
            var c = await repository.Find(id);

            if (c == null)
                throw new NotFoundCustomException($"Resource not found. Id {id}");


            return c;
        }

        public async Task<Customer> Insert(Customer customer)
        {
            return await repository.Insert(customer);
        }

        public async Task<Customer> Update(long id, Customer customer)
        {
            var c = await repository.Find(id);

            if (c == null)
                throw new NotFoundCustomException($"Resource not found. Id {id}");

            c.ativo = customer.ativo;
            c.cpf = customer.cpf;
            c.email = customer.email;
            c.nome = customer.nome;
            c.sobrenome = customer.sobrenome;
            c.rg = customer.rg;
            c.atualizadoEm = DateTime.Now;

            await repository.Update(c);

            return c;
        }

        public async Task Delete(long id)
        {
            var c = await repository.Find(id);
            if (c == null)
                throw new NotFoundCustomException($"Resource not found. Id {id}");

            await repository.Delete(c);
        }
    }
}