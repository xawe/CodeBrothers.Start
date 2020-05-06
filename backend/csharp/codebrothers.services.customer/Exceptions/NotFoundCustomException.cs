using System.Net;

namespace codebrothers.services.customer.Exceptions
{
    public class NotFoundCustomException : BaseCustomException
    {
        public NotFoundCustomException(string message) : base(message, "", (int)HttpStatusCode.NotFound)
        {

        }
    }
}