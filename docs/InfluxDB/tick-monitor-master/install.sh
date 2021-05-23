# mkdir
mkdir /etc/influxdb /etc/telegraf /etc/kapacitor

# touch
touch /etc/influxdb/influxdb.conf /etc/telegraf/telegraf.conf /etc/kapacitor/kapacitor.conf

# write influxdb.conf
echo "[meta]
  dir = \"/var/lib/influxdb/meta\"
[data]
  dir = \"/var/lib/influxdb/data\"
  wal-dir = \"/var/lib/influxdb/wal\"" > /etc/influxdb/influxdb.conf

# write telegraf.conf 
echo "[agent]
  interval = \"10s\"
  round_interval = true
  metric_batch_size = 1000
  metric_buffer_limit = 10000
  collection_jitter = \"0s\"
  flush_interval = \"10s\"
  flush_jitter = \"0s\"
  debug = false
  quiet = false
  hostname = \"your.domain.com\"
  omit_hostname = false

[[outputs.influxdb]]
  urls = [\"http://influxdb:8086\"]
  database = \"telegraf\"
  username = \"\"
  password = \"\"
  write_consistency = \"any\"
  timeout = \"5s\"

[[inputs.docker]]
  endpoint = \"unix:///var/run/docker.sock\"
  container_names = []
  timeout = \"5s\"
  perdevice = true
  total = false

[[inputs.cpu]]
[[inputs.system]]" > /etc/telegraf/telegraf.conf



# write kapacitor.conf
echo "# The hostname of this node.
# Must be resolvable by any configured InfluxDB hosts.
hostname = \"kapacitor\"
# Directory for storing a small amount of metadata about the server.
data_dir = \"/var/lib/kapacitor\"

# Do not apply configuration overrides during startup.
# Useful if the configuration overrides cause Kapacitor to fail startup.
# This option is intended as a safe guard and should not be needed in practice.
skip-config-overrides = false

# Default retention-policy, if a write is made to Kapacitor and
# it does not have a retention policy associated with it,
# then the retention policy will be set to this value
default-retention-policy = \"\"

[http]
  # HTTP API Server for Kapacitor
  # This server is always on,
  # it serves both as a write endpoint
  # and as the API endpoint for all other
  # Kapacitor calls.
  bind-address = \":9092\"
  log-enabled = true
  write-tracing = false
  pprof-enabled = false
  https-enabled = false
  https-certificate = \"/etc/ssl/kapacitor.pem\"
  ### Use a separate private key location.
  # https-private-key = \"\"

[config-override]
  # Enable/Disable the service for overridding configuration via the HTTP API.
  enabled = true

[logging]
    # Destination for logs
    # Can be a path to a file or 'STDOUT', 'STDERR'.
    file = \"/var/log/kapacitor/kapacitor.log\"
    # Logging level can be one of:
    # DEBUG, INFO, ERROR
    # HTTP logging can be disabled in the [http] config section.
    level = \"INFO\"

[load]
  # Enable/Disable the service for loading tasks/templates/handlers
  # from a directory
  enabled = true
  # Directory where task/template/handler files are set
  dir = \"/etc/kapacitor/load\"

[replay]
  # Where to store replay files, aka recordings.
  dir = \"/var/lib/kapacitor/replay\"

[task]
  # Where to store the tasks database
  # DEPRECATED: This option is not needed for new installations.
  # It is only used to determine the location of the task.db file
  # for migrating to the new \`storage\` service.
  dir = \"/var/lib/kapacitor/tasks\"
  # How often to snapshot running task state.
  snapshot-interval = \"60s\"

[storage]
  # Where to store the Kapacitor boltdb database
  boltdb = \"/var/lib/kapacitor/kapacitor.db\"

[deadman]
  # Configure a deadman's switch
  # Globally configure deadman's switches on all tasks.
  # NOTE: for this to be of use you must also globally configure at least one alerting method.
  global = false
  # Threshold, if globally configured the alert will be triggered if the throughput in points/interval is <= threshold.
  threshold = 0.0
  # Interval, if globally configured the frequency at which to check the throughput.
  interval = \"10s\"
  # Id -- the alert Id, NODE_NAME will be replaced with the name of the node being monitored.
  id = \"node 'NODE_NAME' in task '{{ .TaskName }}'\"
  # The message of the alert. INTERVAL will be replaced by the interval.
  message = \"{{ .ID }} is {{ if eq .Level \\\"OK\\\" }}alive{{ else }}dead{{ end }}: {{ index .Fields \\\"collected\\\" | printf \\\"%0.3f\\\" }} points/INTERVAL.\"

# Multiple InfluxDB configurations can be defined.
# Exactly one must be marked as the default.
# Each one will be given a name and can be referenced in batch queries and InfluxDBOut nodes.
[[influxdb]]
  # Connect to an InfluxDB cluster
  # Kapacitor can subscribe, query and write to this cluster.
  # Using InfluxDB is not required and can be disabled.
  enabled = true
  default = true
  name = \"influxdb\"
  urls = [\"http://influxdb:8086\"]
  username = \"\"
  password = \"\"
  timeout = 0
  # Absolute path to pem encoded CA file.
  # A CA can be provided without a key/cert pair
  #   ssl-ca = \"/etc/kapacitor/ca.pem\"
  # Absolutes paths to pem encoded key and cert files.
  #   ssl-cert = \"/etc/kapacitor/cert.pem\"
  #   ssl-key = \"/etc/kapacitor/key.pem\"

  # Do not verify the TLS/SSL certificate.
  # This is insecure.
  insecure-skip-verify = false

  # Maximum time to try and connect to InfluxDB during startup
  startup-timeout = \"5m\"

  # Turn off all subscriptions
  disable-subscriptions = false

  # Subscription mode is either \"cluster\" or \"server\"
  subscription-mode = \"cluster\"

  # Which protocol to use for subscriptions
  # one of 'udp', 'http', or 'https'.
  subscription-protocol = \"http\"

  # Subscriptions resync time interval
  # Useful if you want to subscribe to new created databases
  # without restart Kapacitord
  subscriptions-sync-interval = \"1m0s\"

  # Override the global hostname option for this InfluxDB cluster.
  # Useful if the InfluxDB cluster is in a separate network and
  # needs special config to connect back to this Kapacitor instance.
  # Defaults to `hostname` if empty.
  kapacitor-hostname = \"\"

  # Override the global http port option for this InfluxDB cluster.
  # Useful if the InfluxDB cluster is in a separate network and
  # needs special config to connect back to this Kapacitor instance.
  # Defaults to the port from \`[http] bind-address\` if 0.
  http-port = 0

  # Host part of a bind address for UDP listeners.
  # For example if a UDP listener is using port 1234
  # and \`udp-bind = \"hostname_or_ip\"\`,
  # then the UDP port will be bound to \`hostname_or_ip:1234\`
  # The default empty value will bind to all addresses.
  udp-bind = \"\"
  # Subscriptions use the UDP network protocl.
  # The following options of for the created UDP listeners for each subscription.
  # Number of packets to buffer when reading packets off the socket.
  udp-buffer = 1000
  # The size in bytes of the OS read buffer for the UDP socket.
  # A value of 0 indicates use the OS default.
  udp-read-buffer = 0

  [influxdb.subscriptions]
    # Set of databases and retention policies to subscribe to.
    # If empty will subscribe to all, minus the list in
    # influxdb.excluded-subscriptions
    #
    # Format
    # db_name = <list of retention policies>
    #
    # Example:
    # my_database = [ \"default\", \"longterm\" ]
  [influxdb.excluded-subscriptions]
    # Set of databases and retention policies to exclude from the subscriptions.
    # If influxdb.subscriptions is empty it will subscribe to all
    # except databases listed here.
    #
    # Format
    # db_name = <list of retention policies>
    #
    # Example:
    # my_database = [ \"default\", \"longterm\" ]
" > /etc/kapacitor/kapacitor.conf

# down
docker-compose down

# up
docker-compose up -d