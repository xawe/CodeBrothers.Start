using System;

namespace codebrothers.services.customer.Entities
{
    public class CustomError
    {
        public DateTime timestamp { get; set; }
        public int status { get; set; }
        public string error { get; set; }
        public string message { get; set; }
        public string path { get; set; }

        public CustomError()
        {
            timestamp = DateTime.Now;
        }
    }
}