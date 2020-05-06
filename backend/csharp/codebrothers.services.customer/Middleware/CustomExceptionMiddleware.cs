using System;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Net;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace codebrothers.services.customer.Middleware
{
    public class CustomExceptionMiddleware
    {
        private readonly RequestDelegate _next;

        public CustomExceptionMiddleware(RequestDelegate next)
        {
            _next = next;
        }

        public async Task Invoke(HttpContext context)
        {
            try
            {
                await _next.Invoke(context);
            }
            catch (Exception ex)
            {
                await HandleExceptionAsync(context, ex);
            }
        }

        private async Task HandleExceptionAsync(HttpContext context, Exception exception)
        {
            var response = context.Response;
            var customException = exception as Exceptions.BaseCustomException;
            var statusCode = (int)HttpStatusCode.InternalServerError;
            var message = "Unexpected error";
            var description = "Unexpected error";

            if (null != customException)
            {
                message = customException.Message;
                description = customException.Description;
                statusCode = customException.Code;
            }

            response.ContentType = "application/json";
            response.StatusCode = statusCode;
            await response.WriteAsync(
                JsonSerializer.Serialize(new Entities.CustomError
                {
                    error = "Resource not found!",
                    status = statusCode,
                    message = message,
                    path = context.Request.Path
                }));
        }
    }
}