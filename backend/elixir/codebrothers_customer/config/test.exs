use Mix.Config

# Configure your database
config :codebrothers_customer, CodebrothersCustomer.Repo,
  username: "postgres",
  password: "postgres",
  database: "codebrothers_customer_test",
  hostname: "localhost",
  pool: Ecto.Adapters.SQL.Sandbox

# We don't run a server during test. If one is required,
# you can enable the server option below.
config :codebrothers_customer, CodebrothersCustomerWeb.Endpoint,
  http: [port: 4002],
  server: false

# Print only warnings and errors during test
config :logger, level: :warn
