using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using codebrothers.services.customer.Entities;
using codebrothers.services.customer.Interfaces;
using codebrothers.services.customer.Repositories;

namespace codebrothers.services.customer.Controllers
{
    [ApiController]
    [Route("/api/customers/v1")]
    public class CustomerController : ControllerBase
    {
        private readonly ILogger<CustomerController> logger;
        private ICustomerService customerService;
        public CustomerController(ILogger<CustomerController> logger, ICustomerService customerService)
        {
            this.logger = logger;
            this.customerService = customerService;
        }
        [HttpGet]
        public async Task<IActionResult> FindAll()
        {
            return Ok(await customerService.FindAll());
        }
        [HttpGet("{id}")]
        public async Task<IActionResult> FindById(long id)
        {
            if (!ModelState.IsValid)
                return BadRequest();

            return Ok(await customerService.Find(id));
        }
        [HttpPost]
        public async Task<IActionResult> Insert([FromBody]Customer customer)
        {
            if (!ModelState.IsValid)
                return BadRequest();

            return Ok(await customerService.Insert(customer));
        }
        [HttpPut("{id}")]
        public async Task<IActionResult> Update(long id, [FromBody]Customer customer)
        {
            if (!ModelState.IsValid)
                return BadRequest();

            return Ok(await customerService.Update(id, customer));
        }
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(long id)
        {
            if (!ModelState.IsValid)
                return BadRequest();

            await customerService.Delete(id);
            return NoContent();
        }
    }
}