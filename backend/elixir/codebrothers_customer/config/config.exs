# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.

# General application configuration
use Mix.Config

config :codebrothers_customer,
  ecto_repos: [CodebrothersCustomer.Repo]

# Configures the endpoint
config :codebrothers_customer, CodebrothersCustomerWeb.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "fnmjKXOl55QcjXYXJczhAIzsr1vCK+UHNuMFxnoC7ChpDEvpRVSOBmDBEspvKAvg",
  render_errors: [view: CodebrothersCustomerWeb.ErrorView, accepts: ~w(json)],
  pubsub: [name: CodebrothersCustomer.PubSub, adapter: Phoenix.PubSub.PG2],
  live_view: [signing_salt: "BcMs/N5y"]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env()}.exs"
