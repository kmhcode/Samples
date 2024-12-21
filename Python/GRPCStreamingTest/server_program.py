import random
import math
import grpc
import computation_pb2_grpc
from computation_pb2 import DataItem
from concurrent import futures

class ComputationSupportService(computation_pb2_grpc.ComputeSupportServicer):
    
    def GenerateValues(self, request, context):
        min = request.lower
        max = request.upper
        for i in range(request.count):
            sample = min + (max - min) * random.random()
            yield DataItem(value=sample)
    
    def CombineValues(self, request_iterator, context):
        sum = 0.0
        for msg in request_iterator:
            value = msg.value
            sum += value * value
        return DataItem(value=math.sqrt(sum))

server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
server.add_insecure_port('localhost:4040')
computation_pb2_grpc.add_ComputeSupportServicer_to_server(ComputationSupportService(), server)
server.start()
try:
    server.wait_for_termination()
except KeyboardInterrupt:
    pass

#pip install grpcio grpcio-tools --break-system-packages
#python -m grpc_tools.protoc -I protos --python_out=. --pyi_out=. --grpc_python_out=. computation.proto
