/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.journalkeeper.rpc.codec;

import io.journalkeeper.rpc.header.JournalKeeperHeader;
import io.journalkeeper.rpc.remoting.serialize.CodecSupport;
import io.journalkeeper.rpc.remoting.transport.command.Type;
import io.journalkeeper.rpc.server.DisableLeaderWriteRequest;
import io.netty.buffer.ByteBuf;

/**
 * @author LiYue
 * Date: 2019-03-29
 */
public class DisableLeaderWriteRequestCodec extends GenericPayloadCodec<DisableLeaderWriteRequest> implements Type {
    @Override
    protected void encodePayload(DisableLeaderWriteRequest request, ByteBuf buffer) throws Exception {
        CodecSupport.encodeLong(buffer, request.getTimeoutMs());
        CodecSupport.encodeInt(buffer, request.getTerm());

    }

    @Override
    protected DisableLeaderWriteRequest decodePayload(JournalKeeperHeader header, ByteBuf buffer) throws Exception {
        return new DisableLeaderWriteRequest(
                CodecSupport.decodeLong(buffer),
                CodecSupport.decodeInt(buffer)
                );
    }

    @Override
    public int type() {
        return RpcTypes.DISABLE_LEADER_WRITE_REQUEST;
    }
}
