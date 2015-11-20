/**
 * Copyright 2015 Mickael Jeanroy <mickael.jeanroy@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thebuzzmedia.exiftool.process.executor;

import com.thebuzzmedia.exiftool.exceptions.ProcessException;
import com.thebuzzmedia.exiftool.logs.Logger;
import com.thebuzzmedia.exiftool.logs.LoggerFactory;
import com.thebuzzmedia.exiftool.process.Command;
import com.thebuzzmedia.exiftool.process.CommandExecutor;
import com.thebuzzmedia.exiftool.process.CommandResult;
import com.thebuzzmedia.exiftool.process.handlers.ResultHandler;

import java.io.IOException;
import java.util.List;

import static com.thebuzzmedia.exiftool.commons.IOs.readInputStream;

/**
 * Default Executor.
 */
public class DefaultCommandExecutor implements CommandExecutor {

	/**
	 * Class logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(DefaultCommandExecutor.class);

	// Ensure non instantiation.
	DefaultCommandExecutor() {
	}

	@Override
	public CommandResult execute(Command command) {
		try {
			List<String> args = command.getArguments();
			Process proc = new ProcessBuilder(args).start();
			ResultHandler handler = new ResultHandler();
			readInputStream(proc.getInputStream(), handler);
			return new DefaultCommandResult(proc.exitValue(), handler.getOutput());
		}
		catch (IOException ex) {
			log.error(ex.getMessage(), ex);
			throw new ProcessException(ex);
		}
	}
}